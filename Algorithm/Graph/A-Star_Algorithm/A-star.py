class AStarSearchAgent(GenericSearchAlgorithm):
    """
    Egocentric A* algorithm - actually it is just like BFS but the queue is a priority queue
    """
    def __init__(self):
        """
        A new Agent
        """
        # this line is crucial, otherwise the class is not recognized as an AgentBrainPtr by C++
        GenericSearchAlgorithm.__init__(self)
        # minimize the Manhattan distance
        self.heuristic = manhattan_heuristic
    def enqueue(self, cell):
        (r, c) = cell
        d = self.get_distance(r, c)
        h = self.heuristic(r, c)
        print "Queuing cell (%s, %s), d = %s, h = %s" % (r, c, d, h)
        heappush(self.queue, Cell(d + h, r, c))
    def dequeue(self):
        cell = heappop(self.queue)
        h, r, c = cell.h, cell.r, cell.c
        return (r, c)
class FrontAStarSearchAgent(AStarSearchAgent):
    """
    Egocentric A* algorithm with teleportation between fronts
    """
    def get_action(self, r, c, observations):
        """
        we override the get_action method so that we can teleport from place to place
        """
        if not self.goal: # first, figure out where we are trying to go
            (r2, c2) = self.dequeue()
            get_environment().unmark_maze_agent(r2, c2)
            get_environment().mark_maze_yellow(r2, c2)
            self.goal = (r2, c2)
        # then, check if we can get there
        r2, c2 = self.goal
        dr, dc = r2 - r, c2 - c
        action = get_action_index((dr, dc)) # try to find the action (will return None if it's not there)
        v = self.constraints.get_instance() # make the action vector to return
        # first, is the node reachable in one action?
        if action is not None and observations[2 + action] == 0:
            v[0] = action # if yes, do that action!
        else:
            # if not, we should teleport and return null action
            get_environment().teleport(self, r2, c2)
            v[0] = MAZE_NULL_MOVE
        return v # return the action
class CloningAStarSearchAgent(FrontAStarSearchAgent):
    """
    Egocentric A* algorithm with teleportation between fronts and fronts marked by stationary agents
    """
    def get_action(self, r, c, observations):
        """
        we override the get_action method so that we can spawn marker agents and teleport
        """
        if not self.goal: # first, figure out where we are trying to go
            (r2, c2) = self.dequeue()
            get_environment().unmark_maze_agent(r2, c2)
            get_environment().mark_maze_yellow(r2, c2)
            self.goal = (r2, c2)
        # then, check if we can get there
        r2, c2 = self.goal
        dr, dc = r2 - r, c2 - c
        action = get_action_index((dr, dc)) # try to find the action (will return None if it's not there)
        v = self.constraints.get_instance() # make the action vector to return
        # first, is the node reachable in one action?
        if action is not None and observations[2 + action] == 0:
            v[0] = action # if yes, do that action!
        else:
            # if not, we should teleport and return null action
            get_environment().teleport(self, r2, c2)
            v[0] = 4
        return v # return the action
    def mark_the_front(self, r, c, r2, c2):
        get_environment().mark_maze_green(r2, c2)
        get_environment().mark_maze_agent("data/shapes/character/SydneyStatic.xml", r, c, r2, c2)
    def mark_visited(self, r, c):
        get_environment().unmark_maze_agent(r, c)
        if (r, c) != self.starting_pos:
            get_environment().mark_maze_blue(r, c)